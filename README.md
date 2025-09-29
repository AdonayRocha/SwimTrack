# 🏊‍♂️ SwimTrack - Sistema de Gerenciamento de Treinos de Natação

> Um sistema web completo para nadadores gerenciarem suas sessões de treino, desenvolvido com Spring Boot e integração Firebase.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.10-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-24-orange.svg)](https://openjdk.java.net/)
[![Firebase](https://img.shields.io/badge/Firebase-Enabled-yellow.svg)](https://firebase.google.com/)
[![H2 Database](https://img.shields.io/badge/Database-H2-blue.svg)](https://www.h2database.com/)

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Execução](#-instalação-e-execução)
- [Como Usar](#-como-usar)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Configuração Firebase](#-configuração-firebase)
- [API Endpoints](#-api-endpoints)
- [Banco de Dados](#-banco-de-dados)
- [Troubleshooting](#-troubleshooting)

## 🚀 Funcionalidades

### 🔐 Sistema de Autenticação
- **Login tradicional**: usuário e senha
- **Google Authentication**: login via Firebase/Google
- **Spring Security**: proteção de rotas e sessões
- **Criação automática de usuários** via Google Auth

### 🏊‍♀️ Gerenciamento de Treinos
- **Criar sessões de treino** com:
  - Data e hora
  - Duração (em minutos)
  - Distância percorrida
  - Tipo de treino
  - Notas pessoais
- **Visualizar treinos**: lista completa com paginação
- **Editar treinos**: modificar informações existentes
- **Excluir treinos**: remover registros
- **Detalhes do treino**: visualização completa de cada sessão

### 📊 Interface Moderna
- **Design responsivo**: funciona em desktop, tablet e mobile
- **Bootstrap 5**: interface moderna e limpa
- **Font Awesome**: ícones profissionais
- **Animações CSS**: experiência visual agradável
- **Gradientes personalizados**: tema aquático

## 🛠 Tecnologias Utilizadas

### Backend
- **Spring Boot 3.4.10**: Framework principal
- **Spring Security**: Autenticação e autorização
- **Spring Data JPA**: Persistência de dados
- **Hibernate**: ORM
- **Flyway**: Migração de banco de dados
- **H2 Database**: Banco em memória para desenvolvimento
- **Firebase Admin SDK**: Autenticação Google

### Frontend
- **Thymeleaf**: Template engine
- **Bootstrap 5**: CSS framework
- **Font Awesome 6**: Ícones
- **JavaScript ES6**: Interatividade
- **Firebase SDK**: Autenticação cliente

### Ferramentas
- **Gradle**: Gerenciamento de dependências
- **Java 21+**: Linguagem de programação
- **Git**: Controle de versão

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter:

- **Java 21 ou superior** instalado
- **Git** para clonar o repositório
- **Conexão com internet** para downloads de dependências
- **Navegador moderno** (Chrome, Firefox, Safari, Edge)

### Verificar Instalações

```bash
# Verificar Java
java --version

# Verificar Git
git --version
```

## 🚀 Instalação e Execução

### 1. Clone o Repositório

```bash
git clone https://github.com/AdonayRocha/SwimTrack.git
cd SwimTrack
```

### 2. Execute o Projeto

#### Usando Gradle (Recomendado)

```bash
# Windows
.\gradlew bootRun

# Linux/Mac
./gradlew bootRun
```

#### Usando IDE

1. Importe o projeto na sua IDE favorita
2. Execute a classe `Main.java`
3. Aguarde a inicialização do Spring Boot

### 3. Acesse a Aplicação

Após a inicialização (aproximadamente 3-5 segundos), acesse:

- **Aplicação principal**: http://localhost:8083
- **Console H2**: http://localhost:8083/h2-console
- **Login direto**: http://localhost:8083/login

## 🎯 Como Usar

### 1. Fazer Login

#### Opção 1: Login Tradicional
- **Usuário**: `admin`
- **Senha**: `admin`

#### Opção 2: Google Authentication
- Clique em "Autentique com Google"
- Faça login com sua conta Google
- O usuário será criado automaticamente

### 2. Gerenciar Treinos

#### Criar Novo Treino
1. Na tela principal, clique em **"Novo Treino"**
2. Preencha as informações:
   - **Data**: quando o treino foi realizado
   - **Duração**: tempo em minutos
   - **Distância**: metros nadados
   - **Tipo**: estilo/modalidade (ex: crawl, costas, medley)
   - **Notas**: observações pessoais
3. Clique em **"Salvar"**

#### Visualizar Treinos
- A tela principal mostra todos os treinos em formato de tabela
- Cada treino exibe: data, duração, distância, tipo
- Use os botões de ação para **Ver**, **Editar** ou **Excluir**

#### Editar Treino
1. Clique no botão **"Editar"** (ícone de lápis)
2. Modifique as informações desejadas
3. Clique em **"Salvar Alterações"**

#### Ver Detalhes
1. Clique no botão **"Ver"** (ícone de olho)
2. Visualize todas as informações do treino
3. Acesse opções para editar ou excluir

## 📁 Estrutura do Projeto

```
SwimTrack/
├── src/
│   ├── main/
│   │   ├── java/org/st/
│   │   │   ├── Main.java                    # Classe principal
│   │   │   ├── auth/                        # Autenticação
│   │   │   │   ├── AuthController.java      # API de autenticação
│   │   │   │   ├── AuthWebController.java   # Controlador web
│   │   │   │   ├── FirebaseAuthenticationFilter.java
│   │   │   │   └── UserService.java
│   │   │   ├── config/                      # Configurações
│   │   │   │   ├── SecurityConfig.java      # Spring Security
│   │   │   │   └── FirebaseConfig.java      # Firebase
│   │   │   ├── session/                     # Gestão de treinos
│   │   │   │   ├── TrainingSession.java     # Entidade
│   │   │   │   ├── TrainingSessionService.java
│   │   │   │   ├── TrainingSessionController.java
│   │   │   │   └── TrainingSessionWebController.java
│   │   │   └── user/                        # Gestão de usuários
│   │   │       ├── User.java                # Entidade usuário
│   │   │       ├── UserService.java
│   │   │       └── UserRepository.java
│   │   └── resources/
│   │       ├── application.properties        # Configurações da app
│   │       ├── db/migration/                # Scripts Flyway
│   │       │   ├── V1__create_training_session_table.sql
│   │       │   └── V2__create_users_table.sql
│   │       └── templates/                   # Templates Thymeleaf
│   │           ├── login.html               # Tela de login
│   │           ├── sessions.html            # Lista de treinos
│   │           ├── session-form.html        # Formulário de treino
│   │           ├── session-details.html     # Detalhes do treino
│   │           ├── register.html            # Cadastro
│   │           └── error.html               # Página de erro
│   └── test/                               # Testes unitários
├── build.gradle                           # Configuração Gradle
├── README.md                              # Este arquivo
├── FIREBASE_SETUP.md                      # Configuração Firebase
└── GOOGLE_AUTH_STATUS.md                  # Status da implementação
```

## 🔥 Configuração Firebase (Opcional)

O sistema funciona perfeitamente sem Firebase, mas para habilitar o Google Authentication:

### 1. Criar Projeto Firebase

1. Acesse [Firebase Console](https://console.firebase.google.com/)
2. Clique em **"Criar um projeto"**
3. Nome: `swimtrack-23e56` (ou escolha outro)
4. Configure as opções padrão

### 2. Configurar Authentication

1. No menu lateral: **Authentication**
2. Aba **Sign-in method**
3. Ativar **Google**
4. Adicionar email de suporte

### 3. Obter Configurações

1. Configurações do projeto (⚙️)
2. Seção **Seus apps**
3. Adicionar app web (`</>`)
4. Copiar objeto `firebaseConfig`

### 4. Atualizar Código

No arquivo `src/main/resources/templates/login.html`, linha ~60:

```javascript
const firebaseConfig = {
    apiKey: "sua-api-key-aqui",
    authDomain: "seu-projeto.firebaseapp.com",
    projectId: "seu-projeto-id",
    storageBucket: "seu-projeto.appspot.com",
    messagingSenderId: "123456789",
    appId: "1:123456789:web:abcdef123456"
};
```

## 🔌 API Endpoints

### Autenticação
- `POST /api/auth/login` - Login tradicional
- `POST /api/auth/register` - Registro de usuário
- `POST /api/auth/firebase-login` - Login Firebase

### Treinos (API REST)
- `GET /api/sessions` - Listar todos os treinos
- `GET /api/sessions/{id}` - Buscar treino por ID
- `POST /api/sessions` - Criar novo treino
- `PUT /api/sessions/{id}` - Atualizar treino
- `DELETE /api/sessions/{id}` - Excluir treino

### Páginas Web
- `GET /` - Redireciona para login
- `GET /login` - Página de login
- `GET /sessions` - Lista de treinos
- `GET /sessions/new` - Formulário novo treino
- `GET /sessions/{id}` - Detalhes do treino
- `GET /sessions/edit/{id}` - Editar treino

## 🗄️ Banco de Dados

### Tabelas

#### training_sessions
```sql
CREATE TABLE training_sessions (
    id VARCHAR(255) PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    duration INT NOT NULL,
    distance DOUBLE NOT NULL,
    type VARCHAR(100) NOT NULL,
    notes VARCHAR(1000)
);
```

#### users
```sql
CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
```

### Console H2

Para acessar o banco de dados diretamente:

1. URL: http://localhost:8083/h2-console
2. **JDBC URL**: `jdbc:h2:mem:swimtrackdb`
3. **Username**: `sa`
4. **Password**: (deixe em branco)

## 🔧 Troubleshooting

### Problemas Comuns

#### ❌ Erro: "Port 8083 already in use"
```bash
# Encontrar processo usando a porta
netstat -ano | findstr :8083

# Parar o processo
taskkill /PID <PID> /F
```

#### ❌ Firebase não inicializa
- **Solução**: É normal! A aplicação funciona sem Firebase
- **Para resolver**: Configure conforme [seção Firebase](#-configuração-firebase-opcional)

#### ❌ Java não encontrado
```bash
# Instalar Java 21+
# Windows: usar instalador oficial
# Linux: sudo apt install openjdk-21-jdk
# Mac: brew install openjdk@21
```

#### ❌ Projeto não compila
```bash
# Limpar e recompilar
.\gradlew clean build --refresh-dependencies
```

### Logs e Debugging

O sistema possui logs detalhados habilitados:

- **Spring Security**: DEBUG
- **Hibernate SQL**: DEBUG
- **Aplicação**: INFO

Verifique os logs no terminal para diagnosticar problemas.

---

## 📞 Suporte

Para dúvidas, problemas ou sugestões:

- **GitHub Issues**: [Reportar problema](https://github.com/AdonayRocha/SwimTrack/issues)
- **Email**: seu-email@exemplo.com

## 📄 Licença

Este projeto está sob licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

**Desenvolvido com ❤️ por [AdonayRocha](https://github.com/AdonayRocha)**

🏊‍♂️ **Bons treinos!** 🏊‍♀️