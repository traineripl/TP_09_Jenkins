pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube-Tp9') {
                    sh 'mvn -B sonar:sonar -Dsonar.projectKey=tp9-demo'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Deploy - NE DOIT PAS S’EXECUTER SI GATE ROUGE') {
            steps {
                echo 'Déploiement de démonstration'
            }
        }
    }
}
