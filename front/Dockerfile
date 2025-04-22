# Étape 1 : Build Angular en prod
FROM node:22 as builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

RUN npm run build --omit=dev

# Étape 2 : Serve l'app avec NGINX
FROM nginx:alpine

# Copie la build Angular dans NGINX
COPY --from=builder /app/dist/fronttt /usr/share/nginx/html

# Copie une config nginx custom (optionnel)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
