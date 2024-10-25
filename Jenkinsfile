pipeline {
    agent any

    tools {
        maven 'maven_home'
        jdk 'java_home'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/evy']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/evyws/ShopifyQAFlow.git',
                    ]]
                ])
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    sh 'mvn allure:report'

                    archiveArtifacts artifacts: 'target/site/allure-maven-plugin/**'
                }
            }
        }
    }

    post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }
    }
}