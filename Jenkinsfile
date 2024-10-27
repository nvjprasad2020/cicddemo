pipeline {

    agent any

    environment {
        AWS_REGION = 'eu-north-1'
        EBS_APPLICATION_NAME = 'cicd-demo-app'
        EBS_ENVIRONMENT_NAME = 'cicd-demo-app-env'
        S3_BUCKET = 'elasticbeanstalk-eu-north-1-471112946188'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/nvjprasad2020/cicddemo.git', branch: 'master'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Upload JAR to S3') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-credentials']]) {
                    sh "aws s3 cp target/cicd-demo-0.0.1.jar s3://$S3_BUCKET/cicddemo.jar --region $AWS_REGION"
                }
            }
        }
        stage('Deploy to Elastic Beanstalk') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-credentials']]) {
                    script {
                        def versionLabel = "v${env.BUILD_NUMBER}-${new Date().format('yyyyMMddHHmmss')}"

                        sh "aws elasticbeanstalk create-application-version --application-name $EBS_APPLICATION_NAME --version-label ${versionLabel} --source-bundle S3Bucket=$S3_BUCKET,S3Key=cicddemo.jar --region $AWS_REGION"

                        sh "aws elasticbeanstalk update-environment --environment-name $EBS_ENVIRONMENT_NAME --version-label ${versionLabel} --region $AWS_REGION"

                        timeout(time: 5, unit: 'MINUTES') {
                            waitUntil {
                                script {
                                    def status = sh(
                                        script: "aws elasticbeanstalk describe-environments --environment-names $EBS_ENVIRONMENT_NAME --query 'Environments[0].Status' --output text --region $AWS_REGION",
                                        returnStdout: true
                                    ).trim()
                                    echo "EBS Environment Status: ${status}"
                                    return status == "Ready"
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Run Postman Tests') {
            steps {
                script {
                    sh 'newman run postman/cicddemo.postman_collection.json -e postman/cicd-prod-env.postman_environment.json'
                }
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
