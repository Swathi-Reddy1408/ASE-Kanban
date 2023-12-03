import AuthProvider from "./AuthProvider";
import Routes from "./Routes";

function Main() {
  return (
    <AuthProvider>
      <Routes />
    </AuthProvider>
  );
}

export default Main;