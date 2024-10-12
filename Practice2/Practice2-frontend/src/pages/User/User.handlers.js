async function userLoader({ params }) {
  const { id } = params;
  return fetch(`${import.meta.env.VITE_BASE_URL}/api/users/${id}`);
}

async function userAction({ params, request }) {
  const { id } = params;
  const { user_u, user_name, last_name, age, gender, email } =
    Object.fromEntries(request.formData());

  return fetch(`${import.meta.env.VITE_BASE_URL}/api/users/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      user_u,
      email,
      user_name,
      last_name,
      age,
      gender,
    }),
  });
}

export { userLoader, userAction };
