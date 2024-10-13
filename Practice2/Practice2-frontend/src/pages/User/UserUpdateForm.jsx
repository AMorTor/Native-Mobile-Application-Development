import { Form } from "react-router-dom";
import PropTypes from "prop-types";
import UserForm from "../../components/UserForm";
import Button from "../../components/Button/index.js";

export default function UserUpdateForm({ user }) {
  return (
    <Form action={`/user/${user.id_user}`} method="PUT" className="mt-10">
      <UserForm user={user}>
        <Button name="intent" value="update">
          Guardar
        </Button>
      </UserForm>
    </Form>
  );
}

UserUpdateForm.propTypes = {
  user: PropTypes.shape({
    id_user: PropTypes.number.isRequired,
    user_u: PropTypes.string.isRequired,
    user_name: PropTypes.string.isRequired,
    last_name: PropTypes.string,
    email: PropTypes.string.isRequired,
    rol: PropTypes.string,
    age: PropTypes.number,
    gender: PropTypes.string,
  }),
};
