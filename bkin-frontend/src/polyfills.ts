// Fix for SockJS/StompJS using Node's global object in browser context
(window as any).global = window;
