import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
export default function Register() {
 const { register }=useAuth(); const nav=useNavigate(); const [form,setForm]=useState({name:'',email:'',password:''}); const [error,setError]=useState('');
 const change=e=>setForm({...form,[e.target.name]:e.target.value});
 const submit=async e=>{e.preventDefault();try{await register(form.name,form.email,form.password);nav('/dashboard')}catch(err){setError(err.response?.data?.message||'Registration failed')}};
 return <div className="auth-page"><form className="auth-card" onSubmit={submit}><div className="brand dark">FormFlow<span>.</span></div><h2>Create account</h2><p className="muted">Register as a user to submit forms.</p>{error&&<div className="alert alert-danger">{error}</div>}<label>Name</label><input className="form-control mb-3" name="name" value={form.name} onChange={change} required/><label>Email</label><input className="form-control mb-3" type="email" name="email" value={form.email} onChange={change} required/><label>Password (min. 6 characters)</label><input className="form-control mb-4" type="password" minLength="6" name="password" value={form.password} onChange={change} required/><button className="btn btn-primary w-100">Register</button><p className="mt-3 text-center">Already registered? <Link to="/login">Sign in</Link></p></form></div>;
}
