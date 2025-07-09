from logging import Formatter, Handler
import logging
import sys
import os
import time
import servicemanager
import win32event
import win32service
import win32serviceutil
 
import traceback
import configparser
from metadata.data import Data
from azureutil.blobutil import BlobUtil
from flooidbospublishreceivertest import FlooidBosPublishReceiver
from proton.reactor import Container

config = configparser.ConfigParser()
config.read('config')

def _main():
    
    _configure_logging()
    
    if len(sys.argv) == 1 and \
            sys.argv[0].endswith('.exe') and \
            not sys.argv[0].endswith(r'win32\PythonService.exe'):
        # invoked as non-pywin32-PythonService.exe executable without
        # arguments
        
        # We assume here that we were invoked by the Windows Service
        # Control Manager (SCM) as a PyInstaller executable in order to
        # start our service.
        
        # Initialize the service manager and start our service.
        servicemanager.Initialize()
        servicemanager.PrepareToHostSingle(FlooidBosPublishReceiverService)
        servicemanager.StartServiceCtrlDispatcher()
    
    else:
        # invoked with arguments, or without arguments as a regular
        # Python script
  
        # We support a "help" command that isn't supported by
        # `win32serviceutil.HandleCommandLine` so there's a way for
        # users who run this script from a PyInstaller executable to see
        # help. `win32serviceutil.HandleCommandLine` shows help when
        # invoked with no arguments, but without the following that would
        # never happen when this script is run from a PyInstaller
        # executable since for that case no-argument invocation is handled
        # by the `if` block above.
        if len(sys.argv) == 2 and sys.argv[1] == 'help':
            sys.argv = sys.argv[:1]
             
        win32serviceutil.HandleCommandLine(FlooidBosPublishReceiverService)


    
def _configure_logging():
    
    formatter = Formatter('%(message)s')
    
    handler = _Handler()
    handler.setFormatter(formatter)
    
    logger = logging.getLogger()
    logger.addHandler(handler)
    logger.setLevel(logging.INFO)
    

class _Handler(Handler):
    def emit(self, record):
        servicemanager.LogInfoMsg(record.getMessage())
        
    
class FlooidBosPublishReceiverService(win32serviceutil.ServiceFramework):
    
    
    _svc_name_ = 'FlooidBosPublishReceiver'
    _svc_display_name_ = 'Flooid BOS_PUBLISH Receiver'
    _svc_description_ = 'Flooid BOS_PUBLISH Windows service implemented in Python.'
 
 
    def __init__(self, args):
        win32serviceutil.ServiceFramework.__init__(self, args)
        self._stop_event = win32event.CreateEvent(None, 0, 0, None)
        self._poll_intvl = 20 
 
    def GetAcceptedControls(self):
        result = win32serviceutil.ServiceFramework.GetAcceptedControls(self)
        result |= win32service.SERVICE_ACCEPT_PRESHUTDOWN
        return result

    
    def SvcDoRun(self):
        _log('has started')
        servicemanager.LogMsg(servicemanager.EVENTLOG_INFORMATION_TYPE,
                              servicemanager.PYS_SERVICE_STARTED,
                              (self._svc_name_, ''))
        # self.timeout = 100
        while 1:
            rc = win32event.WaitForSingleObject(self.hWaitStop, win32event.INFINITE)
            _log(f'{rc}')
            if rc == win32event.WAIT_OBJECT_0:
                break
            else:
                server = config['flooidconfig']['server']
                port = config['flooidconfig']['port']

                bospublish = config['ihconfig']['ih_bos_publish_queue']
                ih_container_name = config['ihconfig']['ih_container_name']
                ih_file_name = config['ihconfig']['ih_blob_destination'] + "/" + config['ihconfig']['ih_file_name']
                ih_connection_string = config['ihconfig']['ih_blob_connection_string']
                ih_data = Data(ih_file_name, ih_container_name, None)
                ih_blob_util = BlobUtil(ih_connection_string, ih_data)
                _log('config loaded')


                """if server is not reachable it will keep trying
                following logs will be displayed in loop
                            INFO:proton:Connecting to Url('amqp://localhost:5672/PUBLISH')...
                            INFO:__main__:disconnected
                            ERROR:proton:proton.pythonio: Connection refused to all addresses
                            INFO:proton:Disconnected, already closed 
                            
                    if the public ip of this server where receiver/sender is running is not added in Network config
                    upload/download will fail with error
                    AuthorizationFailure - This request is not authorized to perform this operation.          
                            """

                _log(f'running Container with FlooidBosPublishReceiver {server + ":" + port + "/" + bospublish}')
                Container(FlooidBosPublishReceiver(server + ":" + port + "/" + bospublish, ih_blob_util)).run()
                time.sleep(self._poll_intvl)
        return
            
    def SvcOtherEx(self, control, event_type, data):
        
        # See the MSDN documentation for "HandlerEx callback" for a list
        # of control codes that a service can respond to.
        #
        # We respond to `SERVICE_CONTROL_PRESHUTDOWN` instead of
        # `SERVICE_CONTROL_SHUTDOWN` since it seems that we can't log
        # info messages when handling the latter.
        
        if control == win32service.SERVICE_CONTROL_PRESHUTDOWN:
            _log('received a pre-shutdown notification')
            self._stop()
        else:
            _log('received an event: code={}, type={}, data={}'.format(
                    control, event_type, data))
    

    def _stop(self):
        self.ReportServiceStatus(win32service.SERVICE_STOP_PENDING)
        win32event.SetEvent(self._stop_event)


    def SvcStop(self):
        self._stop()
 

def _log(fragment):
    message = 'The {} service {}.'.format(FlooidBosPublishReceiverService._svc_name_, fragment)
    logging.info(message)
    
    
if __name__ == '__main__':
    _main()
