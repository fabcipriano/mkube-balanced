SIMPLE_APP_SERVICE_URL=$(minikube service simple-app-service --url)
echo $SIMPLE_APP_SERVICE_URL
for i in {1..32}; do wget -O /dev/null ${SIMPLE_APP_SERVICE_URL}/fetch-hello; done

for i in {1..32}; do wget -O /dev/null ${SIMPLE_APP_SERVICE_URL}/fetch-hello-balanced; done