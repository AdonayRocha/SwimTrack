# Configuração do Firebase Google Authentication

## Como configurar o Firebase para o botão "Autentique com Google"

### 1. Acesse o Firebase Console
- Vá para: https://console.firebase.google.com/
- Faça login com sua conta Google

### 2. Selecione/Crie seu projeto
- Se já tem o projeto "swimtrack-23e56", selecione-o
- Caso contrário, crie um novo projeto com este nome

### 3. Configure Authentication
- No menu lateral, clique em "Authentication"
- Vá para a aba "Sign-in method"
- Clique em "Google" e ative o provedor
- Adicione um email de suporte (pode ser o seu próprio)
- Salve as configurações

### 4. Obtenha as configurações do projeto
- Clique no ícone de engrenagem (⚙️) e vá em "Project settings"
- Role para baixo até a seção "Your apps"
- Se não tem um app web ainda:
  - Clique no ícone web `</>`
  - Registre o app com nome "SwimTrack"
  - NÃO marque "Firebase Hosting" por enquanto
- Copie o objeto `firebaseConfig` que será mostrado

### 5. Atualize o código
Substitua a configuração no arquivo `login.html` (linha ~12) pela sua configuração real:

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

### 6. Configurações de Domínio (para produção)
- Nas configurações do projeto Firebase
- Vá em "Authentication" > "Settings" > "Authorized domains"
- Adicione seu domínio de produção (ex: `swimtrack.com`)
- Para desenvolvimento local, `localhost` já está autorizado

### 7. Testando
- Execute a aplicação: `./gradlew bootRun`
- Acesse `http://localhost:8080/login`
- Clique no botão "Autentique com Google"
- Deve abrir popup do Google para login

## Arquivos importantes:
- `login.html` - Template com botão Google e JavaScript
- `AuthController.java` - Endpoint `/api/auth/firebase-login`
- `FirebaseConfig.java` - Configuração backend do Firebase
- `FirebaseAuthenticationFilter.java` - Filtro de autenticação JWT

## Fluxo de funcionamento:
1. Usuário clica no botão Google
2. Popup do Google abre para autenticação
3. Firebase retorna token JWT
4. JavaScript envia token para `/api/auth/firebase-login`
5. Backend valida token e cria/encontra usuário
6. Usuário é redirecionado para `/sessions`

## Problemas comuns:
- **"Firebase não configurado"**: Verifique se as configurações estão corretas
- **"Popup blocked"**: Verifique se o navegador não está bloqueando popups
- **"Domain not authorized"**: Adicione o domínio nas configurações do Firebase
- **"Invalid API key"**: Verifique se a API key está correta e o projeto ativo