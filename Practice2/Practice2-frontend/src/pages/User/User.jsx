import { Form, useActionData, useLoaderData } from "react-router-dom";
import Button from "../../components/Button";
import Layout from "../../components/Layout/index.js";
import UserDeleteDialog from "./UserDeleteDialog.jsx";

export default function User() {
  const user = useLoaderData();
  const errors = useActionData();

  return (
    <Layout>
      <section className="flex gap-6 flex-col md:flex-row justify-between md:items-center">
        <div>
          <h1 className="font-semibold text-gray-900">Perfil</h1>
          <p className="mt-2 text-sm text-gray-700">
            Este es tu perfil de usuario, aquí puedes ver y editar tu
            información
          </p>
        </div>
        <UserDeleteDialog />
      </section>
      <pre>{JSON.stringify(user, null, 2)}</pre>
      <Form action="/user">
        <div>
          <label htmlFor="user_u">Usuario</label>
          <input id="user_u" name="user_u" type="text" required />
        </div>
        <div>
          <label htmlFor="user_name">Nombre</label>
          <input id="user_name" name="user_name" type="text" required />
        </div>
        <div>
          <label htmlFor="last_name">Apellido</label>
          <input id="last_name" name="last_name" type="text" />
        </div>
        <div>
          <label htmlFor="email">Correo</label>
          <input id="email" name="email" type="email" required />
        </div>
        {/* TODO: Only the admin can see this field */}
        <div>
          <label htmlFor="rol">Rol</label>
          <select id="rol" name="rol">
            {["Usuario", "Administrador"].map((rol) => (
              <option key={rol} value={rol}>
                {rol}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="age">Edad</label>
          <input id="age" name="age" type="number" />
        </div>
        <div>
          <label htmlFor="gender">Género</label>
          <select id="gender" name="gender">
            {["Masculino", "Femenino", "Otro"].map((gender) => (
              <option key={gender} value={gender}>
                {gender}
              </option>
            ))}
          </select>
        </div>
        <Button className="w-full">Guardar</Button>
      </Form>
    </Layout>
  );
}
