const CACHE_NAME = 'lucia-portfolio-v1';
const urlsToCache = [
  'https://arya458.github.io/LuciaZuniga/',
  'https://arya458.github.io/LuciaZuniga/index.html',
  'https://arya458.github.io/LuciaZuniga/face192.png',
  'https://arya458.github.io/LuciaZuniga/face512.png',
  'https://arya458.github.io/LuciaZuniga/manifest.json',
  // For now, only cache the main page.
  // Add more files as you create them.
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