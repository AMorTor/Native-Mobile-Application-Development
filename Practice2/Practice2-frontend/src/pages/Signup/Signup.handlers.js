async function signUpAction({ request }) {
  const { email, password } = Object.fromEntries(await request.formData());
  return null;
}

export { signUpAction };
