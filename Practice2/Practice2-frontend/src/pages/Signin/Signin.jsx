import { Form, useActionData } from "react-router-dom";

export default function Signin() {
  const error = useActionData();

  return (
    <main className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
      <section className="sm:mx-auto sm:w-full sm:max-w-sm">
        <h1 className="mt-10 text-center text-3xl md:text-4xl font-bold leading-9 tracking-tight text-gray-900">
          Inicio de sesión
        </h1>
      </section>

      <section className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <Form action="/signin" method="POST" className="space-y-6">
          <div>
            <label
              className="block text-sm font-medium leading-6 text-gray-900 after:content-['*'] after:ml-1 after:text-red-500"
              htmlFor="email"
            >
              Usuario
            </label>
            <div className="mt-2">
              <input
                autoComplete="email"
                className="block w-full rounded-md border-0 px-2.5 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                id="email"
                name="email"
                placeholder="Tu usuario"
                required
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between">
              <label
                className="block text-sm font-medium leading-6 text-gray-900 after:content-['*'] after:ml-1 after:text-red-500"
                htmlFor="password"
              >
                Contraseña
              </label>
            </div>
            <div className="mt-2">
              <input
                autoComplete="current-password"
                className="block w-full rounded-md border-0 px-2.5 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                id="password"
                name="password"
                placeholder="Tu contraseña"
                required
                type="password"
              />
            </div>
          </div>

          {error && <div className="text-red-500 text-sm">{error}</div>}

          <div>
            <button
              className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              type="submit"
            >
              Iniciar sesión
            </button>
          </div>
        </Form>

        <p className="mt-10 text-center text-sm text-gray-500">
          ¿No tienes una cuenta?{" "}
          <a
            className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500"
            href="/signup"
          >
            Crea una cuenta
          </a>
        </p>
      </section>
    </main>
  );
}
