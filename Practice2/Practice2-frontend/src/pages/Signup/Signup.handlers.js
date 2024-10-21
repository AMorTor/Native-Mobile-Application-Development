async function signUpAction({ request }) {
  const { email, password } = Object.fromEntries(await request.formData());

  const res = await fetch(`${import.meta.env.BASE_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      email,
      username: crypto.randomUUID(),
      lastname: crypto.randomUUID(),
      password,
      roles: "USER",
    }),
  });

  if (!res.ok) {
    console.log({ error: res.statusText });
  }
  return res;
}

export { signUpAction };
