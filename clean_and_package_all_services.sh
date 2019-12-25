sudo mvn clean package -f ./services/servicegeo
sudo mvn clean package -f ./services/servicesms
sudo mvn clean package -f ./services/servicepayment

sudo mvn clean package -f ./validators/consumergeo
sudo mvn clean package -f ./validators/consumersms
sudo mvn clean package -f ./validators/consumerpayment

sudo mvn clean package -f ./bdwriters/bdvalidwritergeo
sudo mvn clean package -f ./bdwriters/bdinvalidwritergeo

sudo mvn clean package -f ./bdwriters/bdvalidwritersms
sudo mvn clean package -f ./bdwriters/bdinvalidwritersms

sudo mvn clean package -f ./bdwriters/bdvalidwriterpayment
sudo mvn clean package -f ./bdwriters/bdinvalidwriterpayment

sudo mvn clean package -f ./counter/counterservices

cd ./streams/streamgeo/
sudo rm -r ./checkpoint_geo/
sudo rm -r ./geo_stream_result/
sudo sbt clean
sudo sbt assembly
cd ../../

cd ./streams/streamsms/
sudo rm -r ./checkpoint_sms/
sudo rm -r ./sms_stream_result/
sudo sbt clean
sudo sbt assembly
cd ../../

cd ./streams/streampayment/
sudo rm -r ./checkpoint_payment/
sudo rm -r ./payment_stream_result/
sudo sbt clean
sudo sbt assembly
cd ../../
