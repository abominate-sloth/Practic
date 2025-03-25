import {
  AngularNodeAppEngine,
  createNodeRequestHandler,
  isMainModule,
  writeResponseToNodeResponse,
} from '@angular/ssr/node';
import express from 'express';
import { dirname, resolve } from 'node:path';
import { fileURLToPath } from 'node:url';

const serverDistFolder = dirname(fileURLToPath(import.meta.url));
const browserDistFolder = resolve(serverDistFolder, '../browser');

const app = express();
const angularApp = new AngularNodeAppEngine();

// Список маршрутов, которые должны рендериться на клиенте
const CLIENT_SIDE_ROUTES = [
  '/books/:id/edit',
  '/genres/:id/edit',
  '/authors/:id/edit',
  '/roles/:id/edit',
  '/users/:id/edit',
  '/reviews/:id/edit',
  '/issues/:id/edit'
];

/**
 * Serve static files from /browser
 */
app.use(express.static(browserDistFolder, {
  maxAge: '1y',
  index: false,
  redirect: false,
}));

/**
 * Middleware для проверки маршрутов
 */
app.use((req, res, next) => {
  // Проверяем, является ли маршрут клиентским
  const isClientRoute = CLIENT_SIDE_ROUTES.some(route => {
    const pattern = route.replace(/:\w+/g, '([^/]+)');
    return new RegExp(`^${pattern}$`).test(req.path);
  });

  if (isClientRoute) {
    // Для клиентских маршрутов просто отдаём index.html
    return res.sendFile(resolve(browserDistFolder, 'index.html'));
  }

  // Для остальных - SSR
  next();
});

/**
 * Handle all other requests by rendering the Angular application
 */
app.use('/**', (req, res, next) => {
  angularApp
    .handle(req)
    .then((response) =>
      response ? writeResponseToNodeResponse(response, res) : next(),
    )
    .catch(next);
});

/**
 * Start the server
 */
if (isMainModule(import.meta.url)) {
  const port = process.env['PORT'] || 4000;
  app.listen(port, () => {
    console.log(`Node Express server listening on http://localhost:${port}`);
    console.log(`Client-side routes: ${CLIENT_SIDE_ROUTES.join(', ')}`);
  });
}

export const reqHandler = createNodeRequestHandler(app);
