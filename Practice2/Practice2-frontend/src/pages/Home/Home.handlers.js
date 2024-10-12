async function homeLoader() {
  return fetch(`${import.meta.env.VITE_BASE_URL}/api/users`);
}

export { homeLoader };
