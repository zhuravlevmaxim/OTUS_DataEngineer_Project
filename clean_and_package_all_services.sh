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

cd ./streams/streamgeo/ && sbt clean assembly && cd ../../

cd ./streams/streamsms/ && sbt clean assembly && cd ../../

cd ./streams/streampayment/ && sbt clean assembly && cd ../../
