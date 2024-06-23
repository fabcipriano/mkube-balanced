
# Kubernetes Deployment Guide

## Overview
This guide provides step-by-step instructions on how to create a Minikube cluster on Ubuntu 22.04.1, deploy a Kotlin application, and modify the image pull policy in a Kubernetes deployment.

## Requirements
1. **Ubuntu 22.04.1**
2. **Virtualization Software**: VirtualBox, KVM, or Docker
3. **Minikube**
4. **Kubectl**

## Steps to Create a Minikube Cluster

### Step 1: Install Docker
#### 1. Update your package index and Install Docker:
```bash
sudo apt update
sudo apt install -y docker.io
```
#### 2. Add your user to the Docker group (to run Docker without sudo):
```bash
sudo usermod -aG docker $USER
```
### Step 2: Install Minikube
#### 1. Download Minikube:
```bash
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
```
#### 2. Install Minikube:
```bash
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```

### Step 3: Install Kubectl
#### 1. Download Kubectl:
```bash
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
```
#### 2. Install Kubectl:
```bash
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

### Step 4: Start Minikube with Docker Driver
#### 1. Start Minikube:
```bash
minikube start --driver=docker
```
#### 2. Verify Minikube Status:
```bash
minikube status
```
### Step 5: Use Kubectl to Interact with Minikube
#### 1. Check Cluster Info:
```bash
kubectl cluster-info
```
#### 2. Get Nodes:
```bash
kubectl get nodes
```

Normal text