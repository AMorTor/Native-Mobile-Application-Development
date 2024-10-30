import { Form } from "react-router-dom";
import UserForm from "../../components/UserForm";
import Button from "../../components/Button/index.js";

export default function NewUser() {
  return (
    <>
      <section className="flex gap-6 flex-col md:flex-row justify-between md:items-center">
        <div>
          <h1 className="font-semibold text-gray-900">Nuevo perfil</h1>
          <p className="mt-2 text-sm text-gray-700">
            Aquí puedes crear un nuevo perfil de usuario con la información que
            desees
          </p>
        </div>
      </section>
      <Form action="/user" method="POST" className="mt-10">
        <UserForm>
          <Button>Guardar</Button>
        </UserForm>
      </Form>
    </>
  );
}
