pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    parameters {
        choice(
            name: 'TEST_GROUP',
            choices: ['smoke', 'regression', 'api', 'critical'],
            description: 'TestNG group to execute'
        )

        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Browser for UI tests'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run without displaying the browser'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def command =
                        "mvn clean test" +
                        " -Dgroups=${params.TEST_GROUP}" +
                        " -Dbrowser=${params.BROWSER}" +
                        " -Dheadless=${params.HEADLESS}"

                    if (isUnix()) {
                        sh command
                    } else {
                        bat command
                    }
                }
            }
        }
    }

    post {
        always {
            junit(
                testResults:
                    'target/surefire-reports/TEST-*.xml',
                allowEmptyResults: true
            )

            archiveArtifacts(
                artifacts: 'target/screenshots/**/*.png',
                allowEmptyArchive: true
            )
        }
    }
}