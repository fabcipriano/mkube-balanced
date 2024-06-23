Este projeto demostra o problema de balanceamento do k8s usando o minikube como pode ser demonstrado no artigo:


Desta forma, leia o arquivo README-MINIKUBE.md para criar o cluster para testes.

Depois de criar o cluster voce deve fazer o push da imagem local usando os passos abaixo:

### Step 1: Mude para o Minikube Docker Daemon
#### 1. Mude o docker daemon:
```bash
eval $(minikube docker-env)
```

#### 2. Push da nova imagem para o Minikube Docker:
```bash
# dir image
cd nginx-hello-world/

# Build Docker image
docker build -t nginx-hello-world:latest .

# Load Docker image into Minikube
minikube image load nginx-hello-world:latest
```

#### 3. Deploy do ngix no minikube:
```bash
# Apply the deployment
kubectl apply -f nginx-deployment.yaml

# Expose the deployment
kubectl expose deployment nginx-deployment --type=NodePort --port=80

# Get the service URL
minikube service nginx-deployment --url
```
#### 4. Verifique o "Desbalanceamento":
```bash
cd nginx-hello-world/

./validate-if-k8s-balance-is-working.sh

# See the PODs logs and you will see the PODs not balancing keep-alive connection
kubectl logs -f nginx-deployment-5d8b6d4b6f-abcde
```

