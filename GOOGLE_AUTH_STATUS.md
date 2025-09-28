# SwimTrack Firebase Configuration

## Status: ✅ Implementado

O botão "Autentique com Google" foi implementado seguindo o padrão do EpicTaskX:

### Funcionalidades implementadas:
1. **Frontend (JavaScript)**: Popup do Google usando Firebase SDK
2. **Backend (Spring Boot)**: Endpoint `/api/auth/firebase-login` para receber token
3. **Autenticação automática**: Cria usuário se não existir
4. **Integração com Spring Security**: Token JWT validado pelo `FirebaseAuthenticationFilter`

### Arquivos modificados:
- ✅ `login.html` - Botão Google com Firebase JavaScript SDK
- ✅ `AuthWebController.java` - Endpoint `/auth/google`
- ✅ `AuthController.java` - Endpoint `/api/auth/firebase-login` e classe `FirebaseLoginRequest`
- ✅ `SecurityConfig.java` - Permissões para endpoints Firebase

### Para usar:
1. **Configure o Firebase** (veja `FIREBASE_SETUP.md`):
   - Acesse Firebase Console
   - Ative Google Authentication
   - Obtenha configuração do projeto
   - Substitua em `login.html` (linha ~12)

2. **Teste localmente**:
   ```bash
   ./gradlew bootRun
   ```
   - Acesse: http://localhost:8080/login
   - Clique em "Autentique com Google"

### Fluxo de funcionamento:
```
[Usuário] → [Botão Google] → [Popup Firebase] → [Token JWT] → [Backend] → [/sessions]
```

### Integração com EpicTaskX:
- ✅ Frontend JavaScript (igual ao EpicTaskX)
- ✅ Token enviado para backend via API
- ✅ Validação JWT no backend
- ✅ Criação automática de usuário
- ✅ Redirecionamento após login

**Pronto para usar!** 🚀