import logging
import traceback
import configparser
import datetime
import time
from proton.handlers import MessagingHandler

logging.basicConfig(
    format='%(asctime)s %(levelname)-8s %(message)s',
    level=logging.INFO,
    datefmt='%Y-%m-%d %H:%M:%S',
    filename='flooid_bos_publish_receiver.log',
    encoding='utf-8')
logger = logging.getLogger(__name__)
#logging.basicConfig(filename='flooid_bos_publish_receiver.log', encoding='utf-8', level=logging.INFO)

config = configparser.ConfigParser()
config.read('config')

class FlooidBosPublishReceiver(MessagingHandler):
    def __init__(self, url, blob_util):
        super(FlooidBosPublishReceiver, self).__init__()
        self.url = url
        self.received = 0
        self.blob_util = blob_util

    def on_start(self, event):
        try:
            event.container.create_receiver(self.url)
        except Exception as e:
            traceback.print_exception(type(e), e, e.__traceback__)
            logger.info(f'on_start error occurred')

    """ once on_message is complete message will be removed from queue
        in case of exceptions message will not be removed
        do not catch any exception in on_message
    """
    def on_message(self, event):
        logger.info('on_message started...')
        thistime = time.time()
        year = datetime.datetime.now().year
        month = datetime.datetime.now().strftime("%m")
        day = datetime.datetime.now().strftime("%d")
        #logger.info(f'thistime={thistime}')
        ih_blob_destination = config['ihconfig']['ih_blob_destination'] + "/" + str(year) + "/" + str(month) + "/" + str(day) + "/"
        logger.info(f'ih_blob_destination={ih_blob_destination}')
        self.blob_util.data.on_set_filename(ih_blob_destination + "message" + "_" + str(thistime).replace(".","") + ".xml")
        logger.info(f'file_name={self.blob_util.data.file_name}')
        logger.info(f'event.message.body={event.message.body}')
        self.blob_util.on_upload(self.blob_util.data.file_name, event.message.body)
        self.received += 1
        logger.info(f'on_message is executed')


    def on_session_closed(self, event):
        logger.info(f'session closed. total messages received = {self.received}')
        return super().on_session_closed(event)
    
    def on_disconnected(self, event):
        if (event.receiver != None):
            event.receiver.close()
        event.connection.close()
        logger.info("disconnected")
        return super().on_disconnected(event)
        


