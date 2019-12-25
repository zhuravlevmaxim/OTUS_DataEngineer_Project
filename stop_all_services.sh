sudo kill $(ps aux | grep java | grep ./services | awk '{print $2}')

sudo kill $(ps aux | grep java | grep ./validators | awk '{print $2}')

sudo kill $(ps aux | grep java | grep ./bdwriters | awk '{print $2}')

sudo kill $(ps aux | grep java | grep ./counter | awk '{print $2}')

sudo kill $(ps aux | grep GeoStream | awk '{print $2}')

sudo kill $(ps aux | grep SmsStream | awk '{print $2}')

sudo kill $(ps aux | grep PaymentStream | awk '{print $2}')

sudo docker stop $(sudo docker ps -q)
