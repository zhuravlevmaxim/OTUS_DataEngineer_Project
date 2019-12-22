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


java -jar ./services/servicegeo/target/service-geo.jar &
java -jar ./services/servicesms/target/service-sms.jar &
java -jar ./services/servicepayment/target/service-payment.jar  &

java -jar ./validators/consumergeo/target/consumer-geo.jar &
java -jar ./validators/consumersms/target/consumer-sms.jar &
java -jar ./validators/consumerpayment/target/consumer-payment.jar &

java -jar ./bdwriters/bdvalidwritergeo/target/bd-valid-writer-geo.jar  &
java -jar ./bdwriters/bdinvalidwritergeo/target/bd-invalid-writer-geo.jar &

java -jar ./bdwriters/bdvalidwritersms/target/bd-valid-writer-sms.jar  &
java -jar ./bdwriters/bdinvalidwritersms/target/bd-invalid-writer-sms.jar  &

java -jar ./bdwriters/bdvalidwriterpayment/target/bd-valid-writer-payment.jar  &
java -jar ./bdwriters/bdinvalidwriterpayment/target/bd-invalid-writer-payment.jar  &

java -jar ./counter/counterservices/counter-services.jar &

