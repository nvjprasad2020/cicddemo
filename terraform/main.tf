provider "aws" {
  region = "us-west-2"
}

resource "aws_key_pair" "my_key" {
  key_name   = "my-key"
  public_key = "ssh-rsa YOUR_PUBLIC_KEY_HERE"
}

resource "aws_security_group" "allow_http_ssh" {
  name_prefix = "allow_http_ssh"

  ingress {
    description = "Allow HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Allow SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Allow Jenkins"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "my_ec2" {
  ami           = "ami-0c55b159cbfafe1f0"
  instance_type = "t2.micro"
  key_name      = aws_key_pair.my_key.key_name
  security_groups = [aws_security_group.allow_http_ssh.name]

  # User data script for setup
  user_data = <<-EOF
    #!/bin/bash
    # Update packages
    sudo apt update -y

    # Install AWS CLI
    curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
    unzip awscliv2.zip
    sudo ./aws/install

    # Configure AWS CLI (add your AWS credentials here)
    aws configure set aws_access_key_id YOUR_ACCESS_KEY
    aws configure set aws_secret_access_key YOUR_SECRET_KEY
    aws configure set region YOUR_DEFAULT_REGION
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
  EOF

  tags = {
    Name = "my-ec2-instance"
  }
}

output "ec2_instance_public_ip" {
  value = aws_instance.my_ec2.public_ip
}
