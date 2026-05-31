pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo 'Building application...'
                checkout scm
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }
        
        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t insureme:latest .'
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying container...'
                sh 'docker rm -f insureme-app || true'
                sh 'docker run -d -p 8086:8081 --name insureme-app insureme:latest'
                sh 'sleep 5'
            }
        }
        
        stage('Verify') {
            steps {
                echo 'Verifying API...'
                sh 'curl -s http://localhost:8086/viewPolicy/POL1001'
            }
        }
    }
    
    post {
        success {
            echo '🎉 Build SUCCESSFUL! App running on port 8086'
        }
        failure {
            echo '❌ Build FAILED! Check logs above.'
        }
    }
}
