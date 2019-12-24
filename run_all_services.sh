cd ./docker-compouse && sudo docker-compose up -d && cd ..

sudo java -jar ./services/servicegeo/target/service-geo.jar &
sudo java -jar ./services/servicesms/target/service-sms.jar &
sudo java -jar ./services/servicepayment/target/service-payment.jar  &

sudo java -jar ./validators/consumergeo/target/consumer-geo.jar &
sudo java -jar ./validators/consumersms/target/consumer-sms.jar &
sudo java -jar ./validators/consumerpayment/target/consumer-payment.jar &

sudo java -jar ./bdwriters/bdvalidwritergeo/target/bd-valid-writer-geo.jar &
sudo java -jar ./bdwriters/bdinvalidwritergeo/target/bd-invalid-writer-geo.jar &

sudo java -jar ./bdwriters/bdvalidwritersms/target/bd-valid-writer-sms.jar  &
sudo java -jar ./bdwriters/bdinvalidwritersms/target/bd-invalid-writer-sms.jar  &

sudo java -jar ./bdwriters/bdvalidwriterpayment/target/bd-valid-writer-payment.jar  &
sudo java -jar ./bdwriters/bdinvalidwriterpayment/target/bd-invalid-writer-payment.jar  &

sudo java -jar ./counter/counterservices/target/counter-topic-services.jar &


sudo ~/spark-2.4.4-bin-hadoop2.7/bin/spark-submit --class ru.otus.de.project.streamgeo.GeoStream ./target/scala-2.11/streamgeo-assembly-0.1.jar &

sudo ~/spark-2.4.4-bin-hadoop2.7/bin/spark-submit --class ru.otus.de.project.streamsms.SmsStream ./target/scala-2.11/streamsms-assembly-0.1.jar &

sudo ~/spark-2.4.4-bin-hadoop2.7/bin/spark-submit --class ru.otus.de.project.streampayment.PaymentStream ./target/scala-2.11/streampayment-assembly-0.1.jar &
