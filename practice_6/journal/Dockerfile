FROM node:10-alpine

COPY server.js /server.js
COPY package-lock.json /package-lock.json
COPY package.json /package.json

RUN npm install

CMD node /server.js