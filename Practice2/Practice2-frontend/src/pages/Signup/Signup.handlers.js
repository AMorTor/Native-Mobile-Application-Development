import { saveUserToLocalstorage } from "../../utils/saveUserToLocalstorage.js";
import { redirect } from "react-router-dom";

async function signUpAction({ request }) {
  const { email, username, lastname, password } = Object.fromEntries(
    await request.formData(),
  );

  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      email,
      username,
      lastname,
      password,
    }),
  });

  if (!res.ok) {
    console.log({ error: res.statusText });
  }

  const { token } = await res.json();
  await saveUserToLocalstorage(token);
  return redirect("/");
}

export { signUpAction };
