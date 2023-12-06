const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    createProxyMiddleware('/backend', {
      target: 'http://localhost:8080',
      changeOrigin: false,
    })
  );
};
