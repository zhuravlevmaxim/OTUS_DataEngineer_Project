docker-compouse up -d

java -jar ./services/servicegeo/target/service-geo.jar &
java -jar ./services/servicesms/target/service-sms.jar &
java -jar ./services/servicepayment/target/service-payment.jar  &

java -jar ./validators/consumergeo/target/consumer-geo.jar &
java -jar ./validators/consumersms/target/consumer-sms.jar &
java -jar ./validators/consumerpayment/target/consumer-payment.jar &

java -jar ./bdwriters/bdvalidwritergeo/target/bd-valid-writer-geo.jar &
java -jar ./bdwriters/bdinvalidwritergeo/target/bd-invalid-writer-geo.jar &

java -jar ./bdwriters/bdvalidwritersms/target/bd-valid-writer-sms.jar  &
java -jar ./bdwriters/bdinvalidwritersms/target/bd-invalid-writer-sms.jar  &

java -jar ./bdwriters/bdvalidwriterpayment/target/bd-valid-writer-payment.jar  &
java -jar ./bdwriters/bdinvalidwriterpayment/target/bd-invalid-writer-payment.jar  &

java -jar ./counter/counterservices/target/counter-topic-services.jar
