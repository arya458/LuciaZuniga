const CACHE_NAME = 'lucia-portfolio-v1';
const urlsToCache = [
  '/',
  '/index.html',
  '/manifest.json',
  // Add your icon files here after you create them
  // For example: '/android-chrome-192x192.png',
  // '/android-chrome-512x512.png'
];

self.addEventListener('install', event => {
  // Perform install steps
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(cache => {
        console.log('Opened cache');
        return cache.addAll(urlsToCache);
      })
  );
});

self.addEventListener('fetch', event => {
  event.respondWith(
    caches.match(event.request)
      .then(response => {
        // Cache hit - return response
        if (response) {
          return response;
        }
        return fetch(event.request);
      }
    )
  );
});