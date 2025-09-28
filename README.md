# SwimTrack - Sistema de Gerenciamento de Treinos de Natação

## Configuração do Projeto

### Pré-requisitos
- Java 21
- Gradle

### Configuração do Firebase (Opcional)

1. Copie o arquivo `firebase-adminsdk-example.json` para `firebase-adminsdk.json`
2. Substitua os valores de exemplo pelas suas credenciais reais do Firebase
3. O arquivo `firebase-adminsdk.json` não será commitado (está no .gitignore)

### Como executar

```bash
./gradlew bootRun
```

### Funcionalidades

- ✅ Sistema de autenticação com Spring Security
- ✅ CRUD de sessões de treino
- ✅ Interface web com Thymeleaf
- ✅ Banco de dados H2 (desenvolvimento)
- ✅ Migração de banco com Flyway
- ✅ Integração opcional com Firebase

### Acesso

- URL: http://localhost:8080
- Login padrão: admin / admin
- Console H2: http://localhost:8080/h2-console

### Estrutura do Projeto

```
src/
├── main/
│   ├── java/org/st/
│   │   ├── auth/          # Autenticação e segurança
│   │   ├── config/        # Configurações Spring
│   │   ├── session/       # Gestão de sessões de treino
│   │   └── user/          # Gestão de usuários
│   └── resources/
│       ├── templates/     # Templates Thymeleaf
│       └── db/migration/  # Scripts Flyway
```

### Problemas Conhecidos

- Firebase é opcional - a aplicação funciona sem ele
- Para produção, configure um banco PostgreSQL
- As credenciais Firebase devem ser configuradas manualmente