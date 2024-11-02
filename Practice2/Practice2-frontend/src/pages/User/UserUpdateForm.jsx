import { Form, useActionData } from "react-router-dom";
import { useContext, useEffect } from "react";
import { ToastContext } from "../../components/Toast/ToastProvider.jsx";
import PropTypes from "prop-types";
import UserForm from "../../components/UserForm";
import Button from "../../components/Button/index.js";

export default function UserUpdateForm({ user }) {
  const response = useActionData();
  const { toast } = useContext(ToastContext);

  useEffect(() => {
    if (response?.status === "error") {
      toast({
        title: "Error",
        description: response.message,
        variant: "error",
      });
    }

    if (response?.status === "success") {
      toast({
        title: "Éxito",
        description: response.message,
        variant: "success",
      });
    }
  }, [toast, response]);

  return (
    <Form action={`/user/${user.id}`} method="PUT" className="mt-10">
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
    id: PropTypes.number.isRequired,
    email: PropTypes.string.isRequired,
    lastname: PropTypes.string,
    username: PropTypes.string.isRequired,
    role: PropTypes.string,
  }),
};
