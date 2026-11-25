

import { Navigate, Route, Routes } from 'react-router-dom';
import { useAuth } from './context/AuthContext';
import ProtectedRoute from './components/ProtectedRoute';
import Layout from './components/Layout';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import Forms from './pages/Forms';
import FormBuilder from './pages/FormBuilder';
import FillForm from './pages/FillForm';
import Responses from './pages/Responses';

export default function App() {
  const { user } = useAuth();
  return <Routes>
    <Route path="/login" element={user ? <Navigate to="/dashboard" /> : <Login />} />
    <Route path="/register" element={user ? <Navigate to="/dashboard" /> : <Register />} />
    <Route element={<ProtectedRoute><Layout /></ProtectedRoute>}>
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/forms" element={<Forms />} />
      <Route path="/forms/:id" element={<FillForm />} />
      <Route path="/admin/forms" element={<ProtectedRoute role="ADMIN"><Forms admin /></ProtectedRoute>} />
      <Route path="/admin/forms/new" element={<ProtectedRoute role="ADMIN"><FormBuilder /></ProtectedRoute>} />
      <Route path="/admin/forms/:id/edit" element={<ProtectedRoute role="ADMIN"><FormBuilder /></ProtectedRoute>} />
      <Route path="/admin/responses" element={<ProtectedRoute role="ADMIN"><Responses /></ProtectedRoute>} />
    </Route>
    <Route path="*" element={<Navigate to={user ? '/dashboard' : '/login'} />} />
  </Routes>;
}


