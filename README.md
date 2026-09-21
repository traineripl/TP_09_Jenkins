# TP 9 — Jenkins + SonarQube : Pipeline & Quality Gate

Projet Maven pédagogique correspondant au TP 9.

## Flux

Checkout → Build → Test → SonarQube → Quality Gate → Deploy

Le point central du TP est le blocage automatique du pipeline lorsque le
Quality Gate n'est pas validé.

## Prérequis

- JDK 17+
- Maven
- Jenkins
- SonarQube
- Plugin Jenkins **SonarQube Scanner**
- Un projet SonarQube nommé `tp9-demo`
- Un serveur Jenkins déclaré sous le nom `SonarQube-Tp9`
- Un webhook SonarQube vers :

  `http://<JENKINS_HOST>/sonarqube-webhook/`

- Le token SonarQube doit être stocké dans Jenkins Credentials et ne doit
  jamais être commité.

## Test local

```bash
mvn clean test
```

## Jenkins

Le `Jenkinsfile` contient :

```groovy
withSonarQubeEnv('SonarQube-Tp9') {
    sh 'mvn -B sonar:sonar -Dsonar.projectKey=tp9-demo'
}

timeout(time: 10, unit: 'MINUTES') {
    waitForQualityGate abortPipeline: true
}
```

Le stage `Deploy - NE DOIT PAS S’EXECUTER SI GATE ROUGE` sert de preuve
pédagogique : il ne doit pas être exécuté lorsque le Quality Gate bloque
la Pipeline.

## Démonstration

### Chemin vert

1. Le projet compile.
2. Les tests passent.
3. L'analyse SonarQube est publiée.
4. Le Quality Gate est OK.
5. Jenkins poursuit la Pipeline.

### Chemin rouge

Configurez un Quality Gate dont une condition peut être volontairement
violée, puis introduisez le défaut correspondant dans le projet.
Relancez la Pipeline.

Résultat attendu :

`Quality Gate FAILED → Pipeline bloquée → Deploy non exécuté`

## Structure

```text
tp9-demo/
├── pom.xml
├── Jenkinsfile
├── sonar-project.properties
├── README.md
└── src/
    ├── main/java/com/example/tp9/
    │   ├── App.java
    │   └── Calculator.java
    └── test/java/com/example/tp9/
        └── CalculatorTest.java
```

Le projet reprend le flux, les noms et les éléments techniques du support
TP 9 fourni.
