import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
export default function Login() {
  const { login } = useAuth(); const navigate = useNavigate();
  const [email, setEmail] = useState('admin@example.com'); const [password, setPassword] = useState('Admin@123'); const [error, setError] = useState('');
  const submit = async (e) => { e.preventDefault(); setError(''); try { await login(email, password); navigate('/dashboard'); } catch (err) { setError(err.response?.data?.message || 'Login failed'); } };
  return <div className="auth-page"><form className="auth-card" onSubmit={submit}><div className="brand dark">FormFlow<span>.</span></div><h2>Welcome back</h2><p className="muted">Sign in to continue to your workspace.</p>{error && <div className="alert alert-danger">{error}</div>}<label>Email</label><input className="form-control mb-3" type="email" value={email} onChange={e=>setEmail(e.target.value)} required /><label>Password</label><input className="form-control mb-4" type="password" value={password} onChange={e=>setPassword(e.target.value)} required /><button className="btn btn-primary w-100">Sign in</button><p className="mt-3 mb-0 text-center">New here? <Link to="/register">Create an account</Link></p><small className="demo-hint">Demo: admin@example.com / Admin@123</small></form></div>;
}
