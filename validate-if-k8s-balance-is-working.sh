for i in {1..64}; do wget -O /dev/null http://192.168.49.2:31350/fetch-hello; done

for i in {1..64}; do wget -O /dev/null http://192.168.49.2:31350/fetch-hello-balanced; done