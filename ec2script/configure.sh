#!/bin/bash
# Update packages
sudo apt update -y

sudo apt install zip unzip -y

# Install AWS CLI
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

# Configure AWS CLI (add your AWS credentials here)
aws configure set aws_access_key_id
aws configure set aws_secret_access_key
aws configure set region eu-north-1
aws configure set output json

# Install Java
sudo apt install openjdk-17-jdk -y

# Install Maven
sudo apt install maven -y

# Install Jenkins
sudo wget -O /usr/share/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/" | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins -y
sudo systemctl start jenkins

# Confirm Jenkins is up
curl http://localhost:8080
