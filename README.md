🚀 Projeto MVVM Clean XML + Firebase
🧩 Descrição

Aplicativo Android desenvolvido com arquitetura MVVM + Clean Architecture, com foco total em funcionalidade, sincronização offline-first e integração com Firebase.

⚠️ O visual do app é simples — o foco não foi design, e sim estrutura, lógica e boas práticas.
A ideia foi criar uma base sólida, bem arquitetada, pra demonstrar domínio técnico real.

O app permite cadastro, listagem e sincronização automática de itens entre o banco local (Room) e o remoto (Firestore), utilizando o WorkManager para agendar sincronizações em segundo plano.

Além disso, há integração com Firebase Analytics e Crashlytics para monitoramento, e pipelines CI/CD completas com GitHub Actions.

💡 Projeto propositalmente feito sem Dependency Injection, pra entender as dores reais de instanciar tudo manualmente — e valorizar o uso de DI em projetos grandes.

🏗️ Arquitetura

O app segue os princípios de Clean Architecture e MVVM, dividido em camadas independentes:

data/ → fontes de dados (Firebase + Room) e repositórios

domain/ → casos de uso e modelos de domínio

presentation/ → viewModels, fragments e adapters

worker/ → tarefas em background com WorkManager

core/ → utilitários e integrações com Analytics/Crashlytics

⚙️ Tecnologias Principais
Categoria	Ferramenta / Biblioteca
Linguagem	Kotlin
UI	XML + Material Design
Arquitetura	MVVM + Clean Architecture
Reatividade	LiveData, Kotlin Flow
Banco local	Room
Backend	Firebase Firestore
Autenticação	Firebase Auth
Armazenamento	Firebase Storage
Notificações	WorkManager + POST_NOTIFICATIONS
Monitoramento	Firebase Analytics + Crashlytics
Logs customizados	AnalyticsLogger + CrashlyticsLogger
Testes	JUnit + Jacoco
CI/CD	GitHub Actions
Assinatura automática	Keystore via secrets
Deploy	Firebase App Distribution
🧠 Funcionalidades

✅ Login e cadastro de usuários
✅ Criação e listagem de itens com Room
✅ Sincronização automática com Firestore (WorkManager)
✅ Status visual de “pendente” / “sincronizado”
✅ Funcionalidade offline-first
✅ Notificação ao concluir sincronização
✅ Métricas e erros monitorados com Firebase
✅ CI/CD com build, testes e deploy automatizados

🔄 CI/CD Pipeline

O projeto utiliza duas pipelines GitHub Actions, garantindo qualidade e entrega contínua.

🧪 CI – Continuous Integration

Roda em pull requests e push nas branches feature/** e fix/**.
Executa:

🔍 Detekt (análise estática)

🎨 Ktlint (estilo e formatação)

🧹 Lint Android

🧪 Testes unitários

📊 Jacoco (relatório de cobertura)

🔐 Criação automática do google-services.json

🧠 O objetivo é garantir qualidade e padronização antes de integrar o código.

🚀 CD – Continuous Deployment

Roda em push na branch master, incluindo:

🔏 Build Release assinado automaticamente

📦 Upload do APK como artifact

☁️ Deploy no Firebase App Distribution

📝 Release notes automáticas com commit e autor

💬 Entrega contínua e automática — do push ao Firebase, sem intervenção manual.

🔧 Como Rodar Localmente

Clone este repositório

Adicione o google-services.json dentro da pasta app/

(Opcional) Configure o local.properties com o keystore

Execute ./gradlew assembleDebug

🧑‍💻 Propósito Educacional

Este projeto foi feito intencionalmente sem Hilt ou Koin, para entender:

como gerenciar dependências manualmente

por que o DI é importante em escala

como o WorkManager, ViewModel e Room interagem sem ajuda externa

💬 Em uma próxima versão, o projeto será migrado para Hilt, servindo como comparativo de complexidade e manutenibilidade.

📊 Monitoramento e Testes

Firebase Analytics: métricas de uso e eventos personalizados

Crashlytics: erros capturados automaticamente (e via CrashlyticsLogger)

Jacoco: cobertura de testes com relatório HTML/XML

📹 Demonstração

📺 Assista à demonstração completa do projeto no LinkedIn:
👉 https://www.linkedin.com/in/junior-costa-pereira-447b4b273/

No vídeo mostro:

Criação de itens offline

Sincronização automática com Firestore

Notificação de sucesso (WorkManager)

Coleta de eventos no Firebase

Execução da pipeline de build e deploy

✉️ Contato

👤 Junior Costa
📧 juniorcosta15785@gmail.com

💼 LinkedIn