pipeline {
  agent any

  stages {
  stage('Checkout Code') {
  steps {
  echo "Checking out the code "
  git - v
}
}

stage('Build') {
steps {
echo "Testing and building the jar"
mvn - v
}
}

stage('Upload JAR to S3') {
steps {
echo "copying the jar to S3"
}
}

stage('Deploy to Elastic Beanstalk') {
steps {
echo "deploying to EBS"
}
}
}

post {
success {
echo 'Deployment was successful!'
}
failure {
echo 'Deployment failed!'
}
}
}