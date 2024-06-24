Este projeto demostra o problema de balanceamento do k8s usando o minikube como pode ser demonstrado no artigo:
https://learnk8s.io/kubernetes-long-lived-connections

E resolve usando a opcao mais simples esse problema do balanceamento do k8s

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
docker build -t nginx-hello-world:v1.0.0 .

# Load Docker image into Minikube (if you executed step 1.1 you dont need this command)
# minikube image load nginx-hello-world:v1.0.0

# Verify if image is in minikube
minikube image ls

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

#### 5. Build Docker Image SpringBoot to test ClusterIP service:

```bash
# Build the Docker image
docker build -t simple-app:v0.0.6 .

# Load the Docker image into Minikube
# minikube image load simple-app:v0.0.6

# Apply the deployment and service:
kubectl apply -f simple-app-deployment.yaml

# Get the service URL
# Get the URL for the Spring Boot service and access the /fetch-hello endpoint.
minikube service simple-app-service --url
```

### Step 2: Corrigir balanceamento usando envoy proxy e headerless service
#### 1. Faca o servico "desbalenceado" ser um headerless Service:
```bash
# Apply the headerless deployment and service:
kubectl apply -f nginx-headless-deployment.yaml
```
#### 2. Faco o deploy do envoy proxy para balancear o headerless service:
```bash

# Apply envoy proxy deployment:
kubectl apply -f envoy-configmap.yaml
kubectl apply -f envoy-deployment.yaml
```

#### 3. Faca o client chamar o Servico via envoy proxy, e veja o balanceamento mesmo com keep alive:
```bash
SIMPLE_APP_SERVICE_URL=$(minikube service simple-app-service --url)

# Apply envoy proxy deployment:
wget -O /dev/null ${SIMPLE_APP_SERVICE_URL}/fetch-hello-balanced
```
