def sendmail():
  import smtplib
  fromaddr = 'wangejay@gmail.com'
  toaddrs  = '"wangejay@gmail.com","luffytechinc@gmail.com"'
  subject = 'This is an urgent message'
  msg = 'There was a terrible error that occured and I wanted you to know!'
  # Credentials (if needed)
  username = 'wangejay'
  password = 'zZ367172'
  # The actual mail send
  server = smtplib.SMTP('smtp.gmail.com:587')
  server.starttls()
  server.login(username,password)
  server.sendmail(fromaddr, toaddrs, msg)
  server.quit()

logging.basicConfig(level=logging.INFO,filename="/usr/Gateopen.log")
logger = logging.getLogger('logtest')
delaytime=1 #sec
i=5000000
while i > 0:
    time.sleep(delaytime)
    f = urllib.urlopen("http://192.168.0.121/arduino/digital/3/1") #monitor the response of REST API
    var1 = f.read()
    logger.info(var1)
    if var1[6]=="s": #"Pin D13 set to 0"
        print "OK";
    else:
        sendmail()
        print "reboot"
        time.sleep(10)
        os.system("reboot")
    i-=1
