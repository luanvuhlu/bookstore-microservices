@echo off
start /b mvn compile jib:dockerBuild -f ../../audit  && kind load docker-image luanvv/audit -q
start /b mvn compile jib:dockerBuild -f ../../configuration-server && kind load docker-image luanvv/configuration-server -q
start /b mvn compile jib:dockerBuild -f ../../gateway && kind load docker-image luanvv/gateway -q
start /b mvn compile jib:dockerBuild -f ../../louis-store && kind load docker-image luanvv/louis-store -q
start /b mvn compile jib:dockerBuild -f ../../order-service && kind load docker-image luanvv/order-service -q
start /b mvn compile jib:dockerBuild -f ../../product-service && kind load docker-image luanvv/product-service -q
start /b mvn compile jib:dockerBuild -f ../../discovery-server
pause