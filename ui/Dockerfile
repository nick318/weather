FROM node:20 as build
WORKDIR /opt/app-root/
COPY ./ui .
RUN npm ci
RUN npm run build

FROM node:20 as deploy
WORKDIR /opt/app-root/
EXPOSE 5000
COPY --from=build /opt/app-root/dist .
RUN npm install -g serve@^12.0.1
CMD serve -p 5000 -s .