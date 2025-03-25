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
const indexHtmlPath = resolve(browserDistFolder, 'index.html');

const app = express();
const angularApp = new AngularNodeAppEngine();

// Оптимизированная проверка клиентских маршрутов
const isClientRoute = (path: string): boolean => {
  const clientRoutePatterns = [
    /^\/books\/[^/]+\/edit$/,
    /^\/genres\/[^/]+\/edit$/,
    /^\/authors\/[^/]+\/edit$/,
    /^\/roles\/[^/]+\/edit$/,
    /^\/users\/[^/]+\/edit$/,
    /^\/reviews\/[^/]+\/edit$/,
    /^\/issues\/[^/]+\/edit$/
  ];
  return clientRoutePatterns.some(pattern => pattern.test(path));
};

// Serve static files
app.use(express.static(browserDistFolder, {
  maxAge: '1y',
  index: false,
  redirect: false,
  fallthrough: true // Позволяет продолжить обработку если файл не найден
}));

// Error handling middleware
app.use((err: any, req: express.Request, res: express.Response, next: express.NextFunction) => {
  console.error('Server error:', err);
  res.status(500).send('Internal Server Error');
});

// Client-side routes handler
app.use((req, res, next) => {
  if (isClientRoute(req.path)) {
    return res.sendFile(indexHtmlPath, {
      headers: {
        'Cache-Control': 'no-cache, no-store, must-revalidate'
      }
    });
  }
  next();
});

// SSR handler
app.use('/**', (req, res, next) => {
  angularApp
    .handle(req)
    .then((response) => {
      if (response) {
        writeResponseToNodeResponse(response, res);
      } else {
        // Fallback to client-side rendering if SSR fails
        res.sendFile(indexHtmlPath);
      }
    })
    .catch((err) => {
      console.error('SSR rendering error:', err);
      // Fallback to client-side rendering on error
      res.sendFile(indexHtmlPath);
    });
});

// Server startup
if (isMainModule(import.meta.url)) {
  const port = process.env['PORT'] || 4000;
  app.listen(port, () => {
    console.log(`Server running on http://localhost:${port}`);
    console.log('Client-side routes:');
    console.log('- /books/:id/edit');
    console.log('- /genres/:id/edit');
    console.log('- /authors/:id/edit');
    console.log('- /roles/:id/edit');
    console.log('- /users/:id/edit');
    console.log('- /reviews/:id/edit');
    console.log('- /issues/:id/edit');
  });
}

export const reqHandler = createNodeRequestHandler(app);
