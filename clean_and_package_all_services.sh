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

cd ./streams/streamsgeo/ && sbt clean assembly && cd ../../

cd ./streams/streamssms/ && sbt clean assembly && cd ../../

cd ./streams/streamspayment/ && sbt clean assembly && cd ../../
