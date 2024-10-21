import { jwtDecode } from "jwt-decode";
import { redirect } from "react-router-dom";

async function signInAction({ request }) {
  const { email, password } = Object.fromEntries(await request.formData());

  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      username: email,
      password,
    }),
  });

  if (!res.ok) {
    console.log({ error: res.statusText });
    return res.statusText;
  }

  const { token } = await res.json();
  const { userId } = jwtDecode(token);
  localStorage.setItem("token", token);
  localStorage.setItem("userId", userId);

  return redirect("/");
}

export { signInAction };
