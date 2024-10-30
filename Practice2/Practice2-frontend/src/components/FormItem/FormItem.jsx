import PropTypes from "prop-types";

export default function FormItem({ children, className, ...props }) {
  return (
    <div
      className={`first-of-type:border-t border-b py-6 grid gap-4 items-center grid-cols-1 sm:grid-cols-3 ${className}`}
      {...props}
    >
      {children}
    </div>
  );
}

FormItem.propTypes = {
  children: PropTypes.node.isRequired,
  className: PropTypes.string,
};
